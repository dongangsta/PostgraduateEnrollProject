package edu.dsm.service.impl;

import edu.dsm.dao.TextDao;
import edu.dsm.entity.po.Text;
import edu.dsm.service.TextService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TextServiceImpl implements TextService {

    @Resource TextDao textDao;
    @Override
    public List<Text> getAll(){return textDao.selectAll();}
    @Override
    public Text selectById(Integer textId){return textDao.selectByPrimaryKey(textId);}
    @Override
    public int addText(Text text){return textDao.insert(text);}
}
