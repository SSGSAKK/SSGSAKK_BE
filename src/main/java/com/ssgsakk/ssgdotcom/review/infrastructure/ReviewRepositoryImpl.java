package com.ssgsakk.ssgdotcom.review.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssgsakk.ssgdotcom.purchase.domain.QPurchase;
import com.ssgsakk.ssgdotcom.purchaseproduct.domain.QPurchaseProduct;
import com.ssgsakk.ssgdotcom.review.domain.QReview;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QPurchaseProduct qPurchaseProduct = QPurchaseProduct.purchaseProduct;
    private final QPurchase qPurchase = QPurchase.purchase;
    private final QReview qReview = QReview.review;

    public ReviewRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(ReviewRepositoryImpl.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Long> getWritableReviewProductSeq(String uuid){
        return jpaQueryFactory
                .select(qPurchaseProduct.purchaseProductSeq)
                .from(qPurchaseProduct)
                .where(qPurchaseProduct.purchaseSeq.purchaseuuid.eq(uuid), qPurchaseProduct.productState.eq(8))
                .fetch();
    }

    public List<Long> getWrittenReviewProductSeq(String uuid){
        return jpaQueryFactory
                .select(qPurchaseProduct.purchaseProductSeq)
                .from(qPurchaseProduct)
                .join(qReview)
                .where(qPurchaseProduct.purchaseSeq.purchaseuuid.eq(uuid), qPurchaseProduct.productState.eq(8),
                        qReview.productSeq.eq(qPurchaseProduct.productSeq))
                .fetch();
    }
}
