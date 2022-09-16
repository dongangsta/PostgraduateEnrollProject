package edu.dsm.service;

import edu.dsm.entity.po.Text;

import java.util.List;

public interface TextService extends BaseService<Text> {
    List<Text> getAll();
    Text selectById(Integer textId);
    int addText(Text text);
}
