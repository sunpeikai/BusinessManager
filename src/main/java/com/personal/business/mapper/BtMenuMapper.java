package com.personal.business.mapper;

import com.personal.business.dto.MenuTree;
import com.personal.business.entity.BtMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author spk
 * @since 2019-07-16
 */
public interface BtMenuMapper extends BaseMapper<BtMenu> {

    /**
     * @description 根据用户ID查询菜单
     * @auth sunpeikai
     * @param
     * @return
     */
    List<MenuTree> selectMenusByUserId(Integer userId);

    /**
     * 循环父级菜单
     * @author zhangyk
     * @date 2019/5/6 18:06
     */
    List<MenuTree> selectParentMenu(@Param("list") Set<Integer> menuTrees);

}
