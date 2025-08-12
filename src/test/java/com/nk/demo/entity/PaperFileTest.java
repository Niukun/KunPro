package com.nk.demo.entity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nk.demo.mapper.PaperFileVersionMapper;
import com.nk.demo.service.impl.PaperFileVersionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaperFileTest {

    @Autowired
    private PaperFileVersionServiceImpl paperFileVersionService;

    @Autowired
    private PaperFileVersionMapper paperFileVersionMapper;


    @Test
    public void selectTest(){
        LambdaQueryWrapper<PaperFileVersionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<PaperFileVersionEntity>();
        lambdaQueryWrapper.eq(PaperFileVersionEntity::getFileId, "2be6d41277944d24bb5f1c2db5a2672e");
        List<PaperFileVersionEntity> list = paperFileVersionMapper.selectList(lambdaQueryWrapper);
        System.out.println(list);
    }

    @Test
    public void select2Test(){
        LambdaQueryWrapper<PaperFileVersionEntity> lambdaQueryWrapper = new LambdaQueryWrapper<PaperFileVersionEntity>();
        lambdaQueryWrapper.eq(PaperFileVersionEntity::getFileId, "2be6d41277944d24bb5f1c2db5a2672e");
        List<PaperFileVersionEntity> list = paperFileVersionService.list(lambdaQueryWrapper);
        System.out.println(list);
    }

}
