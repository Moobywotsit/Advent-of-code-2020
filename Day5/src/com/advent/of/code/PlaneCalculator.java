package com.advent.of.code;

public class PlaneCalculator {

    public long getSeatID(String seatNotation) throws Exception {
        MinMaxPlaneSeats currentPlaneSeats = new MinMaxPlaneSeats(0, 127, 0, 7);

        char[] seatNotations = seatNotation.toCharArray();
        for(int i = 0; i < seatNotations.length; i++){
            currentPlaneSeats = handleSeatInstruction(seatNotations[i], currentPlaneSeats, i == seatNotation.length() - 4, i == seatNotation.length() - 1);
        }

        //min and max should be the same now
        //multiply the row by 8, then add the column

        return ((long)currentPlaneSeats.maxPlaneSeatRow * 8) + currentPlaneSeats.maxPlaneSeatColumn;
    }

    private static MinMaxPlaneSeats handleSeatInstruction(char instruction, MinMaxPlaneSeats currRange, boolean lastRow, boolean lastColumn) throws Exception {
        if(instruction == 'F'){
            if(lastRow){
                currRange.maxPlaneSeatRow = currRange.minPlaneSeatRow;
            }else{
                currRange.maxPlaneSeatRow = (currRange.maxPlaneSeatRow + currRange.minPlaneSeatRow) / 2;
            }
        }else if(instruction == 'B'){
            if(lastRow){
                currRange.minPlaneSeatRow = currRange.maxPlaneSeatRow;
            }else{
                currRange.minPlaneSeatRow = (int) Math.round(((long)(currRange.maxPlaneSeatRow + currRange.minPlaneSeatRow) / 2.0));
            }
        }else if(instruction == 'L'){
            if(lastColumn){
                currRange.maxPlaneSeatColumn = currRange.minPlaneSeatColumn;
            }else{
                currRange.maxPlaneSeatColumn = (currRange.maxPlaneSeatColumn + currRange.minPlaneSeatColumn) / 2;
            }
        }else if(instruction == 'R'){
            if(lastColumn){
                currRange.minPlaneSeatColumn = currRange.maxPlaneSeatColumn;
            }else{
                currRange.minPlaneSeatColumn = (int) Math.round((long)(currRange.maxPlaneSeatColumn + currRange.minPlaneSeatColumn) / 2.0);
            }
        }else{
            throw new Exception("Unknown instruction");
        }

        return currRange;
    }

    private class MinMaxPlaneSeats {
        private int minPlaneSeatRow;
        private int maxPlaneSeatRow;
        private int minPlaneSeatColumn;
        private int maxPlaneSeatColumn;

        public MinMaxPlaneSeats(int minPlaneSeatRow, int maxPlaneSeatRow, int minPlaneSeatColumn, int maxPlaneSeatColumn) {
            this.minPlaneSeatRow = minPlaneSeatRow;
            this.maxPlaneSeatRow = maxPlaneSeatRow;
            this.minPlaneSeatColumn = minPlaneSeatColumn;
            this.maxPlaneSeatColumn = maxPlaneSeatColumn;
        }
    }

}
