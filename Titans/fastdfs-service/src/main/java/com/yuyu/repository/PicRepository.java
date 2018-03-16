package com.yuyu.repository;

import com.yuyu.entity.RcPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:14:52
 * ProjectName:Mirco-Service-Skeleton
 */
@Repository
public interface PicRepository extends JpaRepository<RcPic,Integer> {
    @Query(value = "select pic.* from rc_pic pic where pic.user_name=?1",nativeQuery = true)
    List<RcPic> findListByUsername(String username);

}
