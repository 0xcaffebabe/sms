package wang.ismy.manage1.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    @Test
    public void test(){

        assertEquals("admin",adminRepository.findById(1).get().getUsername());
    }
}