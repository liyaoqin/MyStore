package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
    public TaotaoResult getItemParamByCid(long cid);
    public TaotaoResult insertItemParam(TbItemParam itemParam);
    EUDataGridResult getItemParamList(int page,int rows);
}
