package edu.dsm.service.impl;

import edu.dsm.dao.TextMapper;
import edu.dsm.entity.po.Text;
import edu.dsm.service.TextService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TextServiceImpl implements TextService {

    @Resource TextMapper textMapper;
    @Override
    public List<Text> getAll(){return textMapper.selectAll();}
    @Override
    public Text selectById(Integer textId){return textMapper.selectByPrimaryKey(textId);}
    @Override
    public int addText(Text text){return textMapper.insert(text);}
}
