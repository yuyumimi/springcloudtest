package yuyu.service;


import com.yuyu.entity.RcUserEntity;

public interface UserService {
    RcUserEntity findByUsername(String username);
}
