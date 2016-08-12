package com.lufficc.repository;

import com.lufficc.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lcc_luffy on 2016/8/7.
 */
@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    Folder findByName(String name);
}
