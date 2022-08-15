package edu.dsm.converter;

import edu.dsm.entity.po.School;
import edu.dsm.entity.vo.SchoolTestVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchoolConverter {
    SchoolConverter INSTANCT = Mappers.getMapper(SchoolConverter.class);
    @Mappings({
            @Mapping(expression = "java(school.getScore21()-school.getScore20())", target = "score21sub20"),
    })
    SchoolTestVo conver(School school);
}
