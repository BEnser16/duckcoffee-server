package com.duckcoffee.app.dao;
import com.duckcoffee.app.entity.TableSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:3000")
public interface TableSeatRepository extends JpaRepository<TableSeat, Long> {
    List<TableSeat> findBySeatGreaterThanEqual(int seatCount);
}
