package com.yuyu.dao;

import com.yuyu.bo.OpenLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OpenLockRepository extends JpaRepository<OpenLock, Integer> {
    OpenLock findByName(String name);

    @Query("from OpenLock u where u.id=:id")
    OpenLock findOpenLockById(@Param("id") Integer id);

    @Query("from OpenLock u where u.code=:code")
    List<OpenLock> findOpenLockByCode(@Param("code") String code);

    @Override
    List<OpenLock> findAll();
}
