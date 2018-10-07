package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ContentService;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper  contentMapper;
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;
    @Override
    public TaotaoResult insertContent(TbContent content) {
        //补全pojo内容
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        //添加缓存同步逻辑
        try {
            HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
            //通知管理员某个缓存同步失败，这个逻辑知道怎么解决就行，异常之后可以写一个通用的方法，比如给管理员发邮件或者发短信，
            // 可以是处理所有异常的通用逻辑
            //有一个开源的包可以支持发邮件的，把一些配置配好直接可以发邮件
            //如果要发短信，可以调用第三方公司的接口，webservice服务，调一下就可以发短信
            //很简单
        }
        return TaotaoResult.ok();
    }

    @Override
    public EUDataGridResult getContentList(int page, int rows){
        //查询商品列表
        TbContentExample example = new TbContentExample();
        //分页处理
        PageHelper.startPage(page, rows);
        //不加条件默认查询所有
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
