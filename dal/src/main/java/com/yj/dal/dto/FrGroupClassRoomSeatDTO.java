package com.yj.dal.dto;

import com.yj.dal.model.FrGroupClassRoomSeat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 29155 on 2018/12/9.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrGroupClassRoomSeatDTO extends FrGroupClassRoomSeat{
    private int[][] seatNumArr;
}
