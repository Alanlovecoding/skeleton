package dao;

import generated.tables.Receipts;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.Result;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public void update(int id, String tagName) {
        List<ReceiptsRecord> receiptsRecordList =dsl.selectFrom(RECEIPTS)
                .where(RECEIPTS.ID.eq(id))
                .fetch();

        if (receiptsRecordList .isEmpty()){
            return;
        }

        //have this receipt
        List<TagsRecord> tagsRecordList =dsl.selectFrom(TAGS)
                .where(TAGS.ID.eq(id))
                .fetch();

        int flag=0;
        for (TagsRecord i : tagsRecordList){
            if (i.getTag().equals(tagName)){
                flag=1;
            }
        }

        //not have this tag
        if(flag==0){
            dsl.insertInto(TAGS, TAGS.ID, TAGS.TAG)
                    .values(id, tagName)
                    .execute();
        }
        else{  //have this tag
            dsl.deleteFrom(TAGS)
                    .where(TAGS.ID.eq(id))
                    .and(TAGS.TAG.eq(tagName))
                    .execute();
        }
    }


    public List<ReceiptsRecord> getbytag(String tagName) {

        //List<Record> recordList = dsl.selectFrom(RECEIPTS.innerJoin(TAGS).on(RECEIPTS.ID.equal(TAGS.ID))).fetch();
        Result rel= dsl.select(TAGS.ID).from(TAGS).where(TAGS.TAG.eq(tagName)).fetch();

        return dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.in(rel)).fetch();
    }
}
