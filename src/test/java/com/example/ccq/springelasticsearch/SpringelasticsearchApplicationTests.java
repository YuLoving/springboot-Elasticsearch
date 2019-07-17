package com.example.ccq.springelasticsearch;

import com.example.ccq.springelasticsearch.dao.ItemRepository;
import com.example.ccq.springelasticsearch.pojo.Item;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringelasticsearchApplication.class)
public class SpringelasticsearchApplicationTests {

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Autowired
    private ItemRepository itemRepository;


    /**
     * 创建索引，会根据Item类的@Document注解信息来创建
     */
    @Test
    public void contextindex() {
        //ElasticsearchTemplate中提供了创建索引的API：
        esTemplate.createIndex(Item.class);
        // 配置映射，会根据Item类中的@Id、@Field等字段来自动完成映射
        esTemplate.putMapping(Item.class);
    }

    /**
     * 删除索引
     */

    @Test
    public void deleteindex(){
        esTemplate.deleteIndex(Item.class);
    }

    //定义一个新增方法
    @Test
    public void insert(){
        Item item = new Item(1L, "小米MIX4", "手机", "小米", 4999.00, "https://img14.360buyimg.com/n1/s450x450_jfs/t1/1867/31/11716/401006/5bd072f8E6db292ab/f3610e2e816ade0f.jpg");
        itemRepository.save(item);

    }

    //批量新增
    @Test
    public  void insertList(){
        List<Item> list = new ArrayList<>();
        //list.add(new Item(2L,"华为P30 Pro","手机","华为",5488.00,"https://img14.360buyimg.com/n7/jfs/t1/25194/10/12869/72820/5c9adf3aEeb4a1c24/da199ff0cbd20f52.jpg"));
        //list.add(new Item(3L,"一加7 Pro","手机","一加",4499.00,"https://img11.360buyimg.com/n7/jfs/t30040/100/1290632710/208879/1f7e2225/5cdd0d92Nb78895a6.jpg"));

        list.add(new Item(4L, "小米手机7", "手机", "小米", 3299.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(5L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(6L, "华为META10", "手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(7L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(8L, "荣耀V10", "手机", "华为", 2799.00, "http://image.baidu.com/13123.jpg"));



        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);

    }

    /**
     * elasticsearch中本没有修改，它的修改原理是该是先删除在新增
     * 修改和新增是同一个接口，区分的依据就是id。
     */

    @Test
    public  void update(){
        Item item = new Item(1L, "小米MIX3", "手机", "小米", 3999.00, "https://img14.360buyimg.com/n1/s450x450_jfs/t1/1867/31/11716/401006/5bd072f8E6db292ab/f3610e2e816ade0f.jpg");
        itemRepository.save(item);
    }

    /**
     * 定义查询方法,含对价格的降序、升序查询
     */
    @Test
    public void testquery(){
        long a=System.currentTimeMillis();
        //查询所有
        //  Iterable<Item> all = itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Item> all = itemRepository.findAll(Sort.by("price").ascending());
        for (Item item:all) {
            System.out.println(item.toString());
        }
        long b=System.currentTimeMillis()-a;
        System.out.println(b);

    }

    /**
     * @Description:按照价格区间查询
     */
    @Test
    public void queryByPriceBetween(){
        List<Item> list = this.itemRepository.findByPriceBetween(2000.00, 3500.00);
        for (Item item : list) {
            System.out.println("item = " + item);
        }
    }

    /**
     * 自定义查询
     *matchQuery底层采用的是词条匹配查询
     */
    @Test
    public void testmatchquery(){

        /**
        * NativeSearchQueryBuilder：Spring提供的一个查询条件构建器，帮助构建json格式的请求体
        *
        *QueryBuilders.matchQuery(“title”, “小米手机”)：利用QueryBuilders来生成一个查询。
        *  QueryBuilders提供了大量的静态方法，用于生成各种不同类型的查询
        *
        *Page<item>：默认是分页查询，因此返回的是一个分页的结果对象，包含属性：
            totalElements：总条数
            totalPages：总页数
             Iterator：迭代器，本身实现了Iterator接口，因此可直接迭代得到当前页的数据
        * */
        //构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加分词查询
        // matchQuery:底层就是使用的termQuery
        queryBuilder.withQuery(QueryBuilders.matchQuery("title","小米手机"));
        //搜索，获取结果
        Page<Item> items = itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("total="+total);

        for (Item item:items) {
            System.out.println(item);
        }
    }


    /**
     * @Description:
     * termQuery:功能更强大，除了匹配字符串以外，还可以匹配
     * int/long/double/float/....
     */
    @Test
    public void testTermQuery(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery("price",2799.0));
        //查找
        Page<Item> items = itemRepository.search(queryBuilder.build());
        for (Item item:items) {
            System.out.println(item);
        }
        }

    /**
     *  @Description:布尔查询
     */
    @Test
    public void testboolean(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("title","华为"))
                                                   .must(QueryBuilders.matchQuery("brand","华为"))


        );
        //查找
        Page<Item> search = itemRepository.search(builder.build());
        for (Item item:
             search) {
            System.out.println(item);
        }

    }

    /**
     * 模糊查询
     */
    @Test
    public void testfuzzy(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.fuzzyQuery("title","华为"));
        Page<Item> search = itemRepository.search(builder.build());
        for (Item item :search
             ) {
            System.out.println(item);
        }
    }

    /**
     * 分页查询
     * Elasticsearch中的分页是从第0页开始。
     */
    @Test
    public void pagetest(){
        //构建查询条件
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //添加基本分词查询
        builder.withQuery(QueryBuilders.termQuery("category","手机"));
        //分页
        int page=0;//第一页
        int size=2;//每页两条
        builder.withPageable(PageRequest.of(page,size));

        //获取结果
        Page<Item> search = itemRepository.search(builder.build());
        //总条数
        long total = search.getTotalElements();
        System.out.println("总条数："+total);
        System.out.println("总页数："+search.getTotalPages());
        //当前页
        System.out.println("当前页："+search.getNumber());
        System.out.println("每页大小："+search.getSize());

        for (Item item:search) {
            System.out.println(item);
        }

    }

    /**
     * 排序查询
     */
    @Test
    public void searchAndSort(){
        // 构建查询条件
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        builder.withQuery(QueryBuilders.termQuery("category", "手机"));
        //排序
        builder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));

        //查询结果
        Page<Item> search = itemRepository.search(builder.build());
        for (Item item:search
             ) {
            System.out.println(item);
        }
    }

    /**
     * 注意：在ES中，需要进行聚合、排序、过滤的字段其处理方式比较特殊，因此不能被分词。
     * 这里我们将color和make这两个文字类型的字段设置为keyword类型，这个类型不会被分词，将来就可以参与聚合
     *
     */

    //桶就是分组，比如这里我们按照品牌brand进行分组：

            /**
             * AggregationBuilders：聚合的构建工厂类。所有聚合都由这个类来构建
                     *  （1）统计某个字段的数量
                     *   ValueCountBuilder vcb=  AggregationBuilders.count("count_uid").field("uid");
                     * （2）去重统计某个字段的数量（有少量误差）
                     *  CardinalityBuilder cb= AggregationBuilders.cardinality("distinct_count_uid").field("uid");
                     * （3）聚合过滤
                     * FilterAggregationBuilder fab= AggregationBuilders.filter("uid_filter").filter(QueryBuilders.queryStringQuery("uid:001"));
                     * （4）按某个字段分组
                     * TermsBuilder tb=  AggregationBuilders.terms("group_name").field("name");
                     * （5）求和
                     * SumBuilder  sumBuilder=	AggregationBuilders.sum("sum_price").field("price");
                     * （6）求平均
                     * AvgBuilder ab= AggregationBuilders.avg("avg_price").field("price");
                     * （7）求最大值
                     * MaxBuilder mb= AggregationBuilders.max("max_price").field("price");
                     * （8）求最小值
                     * MinBuilder min=	AggregationBuilders.min("min_price").field("price");
                     * （9）按日期间隔分组
                     * DateHistogramBuilder dhb= AggregationBuilders.dateHistogram("dh").field("date");
                     * （10）获取聚合里面的结果
                     * TopHitsBuilder thb=  AggregationBuilders.topHits("top_result");
                     * （11）嵌套的聚合
                     * NestedBuilder nb= AggregationBuilders.nested("negsted_path").path("quests");
                     * （12）反转嵌套
                     * AggregationBuilders.reverseNested("res_negsted").path("kps ");
             *
             *  AggregatedPage：聚合查询的结果类。它是Page<T>的子接口：
             *  AggregatedPage在Page功能的基础上，拓展了与聚合相关的功能，它其实就是对聚合结果的一种封装。
             */
    @Test
    public void testagg(){
        //构建查询条件
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //不查询任何结果
         builder.withSourceFilter(new FetchSourceFilter(new String[]{""},null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        builder.addAggregation(AggregationBuilders.terms("brands").field("brand"));
        //2.查询，需要把结果强转为AggregatedPage类型
       AggregatedPage<Item> aggpage= (AggregatedPage<Item>) itemRepository.search(builder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
       StringTerms agg= (StringTerms) aggpage.getAggregation("brands");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket:buckets ) {
            // 3.4、获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            //获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        }


    }


    /**
     * 嵌套聚合，求平均值
     */

    @Test
    public void testsubagg(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //不查询然后结果
        builder.withSourceFilter(new FetchSourceFilter(new String[]{""},null));
        //添加一个新的聚会，聚合类型为terms,聚合名称为brands,聚合字段为brand
        builder.addAggregation(
                AggregationBuilders.terms("brands").field("brand")
                .subAggregation(AggregationBuilders.avg("priceavg").field("price")) // 在品牌聚合桶内进行嵌套聚合，求平均值
        );
        //查询，把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggregatedPage = (AggregatedPage<Item>) itemRepository.search(builder.build());
        //解析
        //从结果中取出名为brands的那个聚合，
        //因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggregatedPage.getAggregation("brands");
        //获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        //遍历
        for (StringTerms.Bucket bucket:buckets) {
            //获取桶中的key，即品牌名称.获取桶中的文档数量
            System.out.println("品牌："+bucket.getKeyAsString()+"数量："+bucket.getDocCount());
            //获取子聚合结果：
           InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceavg");
            System.out.println("平均售价："+avg.getValue());
        }

    }

}
