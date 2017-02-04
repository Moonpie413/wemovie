package personal.wxh.wemovie.http.douban.request;

import personal.wxh.wemovie.http.douban.modules.DoubanSearchResult;

import java.io.IOException;

/**
 * 作者: wangxh
 * 创建日期: 17-2-4
 * 简介: 调用豆瓣的搜索接口搜索
 */
public interface IDoubanSearch {
    /**
     * 根据关键字查找
     * @param  keyType 搜索类型
     * @param keyword 关键字
     * @param count 结果数目
     * @param start 开始索引
     * @return 查询结果
     */
    DoubanSearchResult search(KeyType keyType, String keyword, String count, String start) throws IOException;

}
