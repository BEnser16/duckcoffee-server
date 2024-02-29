package com.duckcoffee.app.service;

import com.duckcoffee.app.dao.ReservationRepository;
import com.duckcoffee.app.dao.TableSeatRepository;
import com.duckcoffee.app.entity.Reservation;
import com.duckcoffee.app.entity.TableSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TableSeatRepository tableSeatRepository;



    public List<LocalTime> findAvailableTimeSlots(LocalDate bookingDate, int peoples) {
        List<LocalTime> availableTimeSlots = new ArrayList<>();
        LocalTime openingTime = LocalTime.of(9, 0);
        LocalTime closingTime = LocalTime.of(22, 0);

        // 生成全天的时段列表
        for (LocalTime time = openingTime; !time.isAfter(closingTime.minusHours(1)); time = time.plusHours(1)) {
            availableTimeSlots.add(time);
        }

        // 找到符合人数需求的桌位
        List<TableSeat> suitableSeats = tableSeatRepository.findBySeatGreaterThanEqual(peoples);

        // 如果没有找到符合人数需求的桌位，直接返回空的可用时段列表
        if (suitableSeats.isEmpty()) {
            return new ArrayList<>();
        }

        // 获取所有符合人数要求的桌位ID
        List<Long> suitableTableIds = suitableSeats.stream().map(TableSeat::getId).collect(Collectors.toList());

        // 获取这些桌位的预订情况
        List<Reservation> reservations = reservationRepository.findByBookingDateAndTableSeatIdIn(bookingDate, suitableTableIds);

        // 对每个时间段进行检查
        availableTimeSlots = availableTimeSlots.stream().filter(timeSlot -> {
            // 对于每个时间段，检查是否所有符合人数要求的桌位都已被预订
            long count = reservations.stream().filter(reservation ->
                    reservation.getStartTime().equals(timeSlot) &&
                            suitableTableIds.contains(reservation.getTableSeat().getId())
            ).count();

            // 如果这个时间段的已预订桌位数量小于符合人数要求的桌位数量，说明至少有一个桌位是可用的
            return count < suitableTableIds.size();
        }).collect(Collectors.toList());

        return availableTimeSlots;
    }

    public Long assignTableForReservation(LocalDate bookingDate , LocalTime startTime , int people) {
        // 找到符合人数的座位列表
        List<TableSeat> suitableSeats = tableSeatRepository.findBySeatGreaterThanEqual(people);

        // 依照日期和时间筛选出订位记录
        List<Reservation> reservationsByDateAndTime = reservationRepository
                .findByBookingDateAndStartTime(bookingDate, startTime);

        // 从符合条件的座位中排除已经在该日期和时间有预订的座位
        List<Long> reservedTableIds = reservationsByDateAndTime.stream()
                .map(reservation -> reservation.getTableSeat().getId())
                .toList();

        // 得到最终的可用座位列表
        List<TableSeat> availableSeats = suitableSeats.stream()
                .filter(seat -> !reservedTableIds.contains(seat.getId()))
                .toList();

        // 检查是否有可用座位
        if (availableSeats.isEmpty()) {
            return null; // 或者抛出一个异常，表示没有可用座位
        }

        // 返回第一个可用座位的ID
        return availableSeats.get(0).getId();
    }


}
