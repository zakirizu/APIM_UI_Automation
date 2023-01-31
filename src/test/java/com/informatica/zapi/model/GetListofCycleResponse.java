package com.informatica.zapi.model;

import java.util.Objects;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * GetListofCycleResponse
 */
@JsonDeserialize(using = ListofCycleDeserializer.class)
public class GetListofCycleResponse {

    private Integer recordsCount;

    private java.util.List<Cycle> cycles = new java.util.ArrayList<Cycle>();

    public GetListofCycleResponse cycles(java.util.List<Cycle> cycles) {
        this.cycles = cycles;
        return this;
    }

    public GetListofCycleResponse addCycle(Cycle cycle) {
        this.cycles.add(cycle);
        return this;
    }

    /**
     * Get cycles
     *
     * @return cycles
     **/
    public java.util.List<Cycle> getcycles() {
        return cycles;
    }

    public void set10208(java.util.List<Cycle> cycles) {
        this.cycles = cycles;
    }

    public GetListofCycleResponse recordsCount(Integer recordsCount) {
        this.recordsCount = recordsCount;
        return this;
    }

    /**
     * Get recordsCount
     *
     * @return recordsCount
     **/
    public Integer getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(Integer recordsCount) {
        this.recordsCount = recordsCount;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetListofCycleResponse GetListofCycleResponse = (GetListofCycleResponse) o;
        return Objects.equals(this.cycles, GetListofCycleResponse.cycles)
                && Objects.equals(this.recordsCount, GetListofCycleResponse.recordsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cycles, recordsCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetListofCycleResponse {\n");

        sb.append("    Cycles: ").append(toIndentedString(cycles)).append("\n");
        sb.append("    recordsCount: ").append(toIndentedString(recordsCount)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
