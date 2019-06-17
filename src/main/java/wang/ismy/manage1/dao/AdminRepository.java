package wang.ismy.manage1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wang.ismy.manage1.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM admin WHERE username = ?")
    Admin findByUsername(String username);
}
