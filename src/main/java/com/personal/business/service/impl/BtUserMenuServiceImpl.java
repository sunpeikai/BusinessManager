package com.personal.business.service.impl;

import com.personal.business.entity.BtUserMenu;
import com.personal.business.mapper.BtUserMenuMapper;
import com.personal.business.request.PermissionsRequest;
import com.personal.business.service.IBtUserMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和菜单关联表 服务实现类
 * </p>
 *
 * @author spk
 * @since 2019-07-16
 */
@Service
public class BtUserMenuServiceImpl extends ServiceImpl<BtUserMenuMapper, BtUserMenu> implements IBtUserMenuService {

    /**
     * @description 用户授权
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public boolean authorize(PermissionsRequest request) {
        return false;
    }

    /**
     * @description 用户取消授权操作
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public boolean unAuthorize(PermissionsRequest request) {
        return false;
    }
}
