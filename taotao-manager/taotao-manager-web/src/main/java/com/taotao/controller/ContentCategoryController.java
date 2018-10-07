package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.util.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value="id",defaultValue = "0")Long parentId) {
        List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
        return list;
    }
    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name) {
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }
    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(Long id,String name){
        TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
        return result;
}
   /* @RequestMapping("delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(@RequestParam(value="parentId",defaultValue = "0")long parentId,long id){
        TaotaoResult result = contentCategoryService.deleteContentCategory(parentId, id);
        return result;
    }*/

}
