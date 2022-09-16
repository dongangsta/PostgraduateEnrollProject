package edu.dsm.service.impl;

import edu.dsm.dao.TextMapper;
import edu.dsm.entity.po.Text;
import edu.dsm.service.TextService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextServiceImpl extends BaseServiceImpl<TextMapper,Text> implements TextService {

    @Override
    public List<Text> getAll(){return this.getBaseMapper().selectAll();}
    @Override
    public Text selectById(Integer textId){return this.getBaseMapper().selectByPrimaryKey(textId);}
    @Override
    public int addText(Text text){return this.getBaseMapper().insert(text);}
}
