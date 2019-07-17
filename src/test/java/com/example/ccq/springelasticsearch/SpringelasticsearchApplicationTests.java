package com.example.ccq.springelasticsearch;

import com.example.ccq.springelasticsearch.dao.ItemRepository;
import com.example.ccq.springelasticsearch.pojo.Item;
import org.elasticsearch.index.query.QueryBuilders;
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



}
