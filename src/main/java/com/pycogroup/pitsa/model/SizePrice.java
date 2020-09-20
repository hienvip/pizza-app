package com.pycogroup.pitsa.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SizePrice {
    @Getter
    @Setter
    private double sizeS;

    @Getter
    @Setter
    private double sizeM;

    @Getter
    @Setter
    private double sizeL;

}
