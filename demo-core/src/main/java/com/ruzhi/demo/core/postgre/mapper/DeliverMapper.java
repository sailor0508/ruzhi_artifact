package com.ruzhi.demo.core.postgre.mapper;

import com.ruzhi.demo.core.test.Deliver;
import com.ruzhi.demo.core.test.DeliverExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DeliverMapper {
    int countByExample(DeliverExample example);

    int deleteByExample(DeliverExample example);

    @Delete({
        "delete from deliver",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    int insertSelective(Deliver record);

    List<Deliver> selectByExample(DeliverExample example);

    @Select({
        "select",
        "id, name, id_no, sp_id, gmt_create, gmt_modified, status, phone",
        "from deliver",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Deliver selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Deliver record, @Param("example") DeliverExample example);

    int updateByExample(@Param("record") Deliver record, @Param("example") DeliverExample example);

    int updateByPrimaryKeySelective(Deliver record);

    @Update({
        "update deliver",
        "set name = #{name,jdbcType=VARCHAR},",
          "id_no = #{idNo,jdbcType=BIGINT},",
          "sp_id = #{spId,jdbcType=BIGINT},",
          "gmt_create = #{gmtCreate,jdbcType=TIME},",
          "gmt_modified = #{gmtModified,jdbcType=TIME},",
          "status = #{status,jdbcType=INTEGER},",
          "phone = #{phone,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Deliver record);
}