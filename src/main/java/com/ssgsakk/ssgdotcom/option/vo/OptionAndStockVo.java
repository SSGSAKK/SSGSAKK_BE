package com.ssgsakk.ssgdotcom.option.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.AbstractMap;
import java.util.List;

@Builder
@Getter
public class OptionAndStockVo {
    private Long productSeq;
    private List<AbstractMap.SimpleEntry<Long, String>> size;
    private List<AbstractMap.SimpleEntry<Long, String>> color;
    private List<AbstractMap.SimpleEntry<Long, String>> customizationOption;
}
