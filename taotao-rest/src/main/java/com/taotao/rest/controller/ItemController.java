package com.taotao.rest.controller;

import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
        TaotaoResult result = itemService.getItemBaseInfo(itemId);
        return result;
    }
    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDesc(@PathVariable Long itemId) {
        TaotaoResult result = itemService.getItemDesc(itemId);
        return result;
    }
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParam(@PathVariable Long itemId) {
        TaotaoResult result = itemService.getItemParam(itemId);
        return result;
    }




}


