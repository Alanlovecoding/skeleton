package controllers;

import api.TagResponse;
import dao.TagDao;
import api.ReceiptResponse;
import dao.ReceiptDao;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;
    final ReceiptDao receipts;

    public TagController(TagDao tags, ReceiptDao receipts) {
        this.tags = tags;
        this.receipts= receipts;
    }

    @PUT
    @Path("/{tag}")
    public void toggleTag(@PathParam("tag") String tagName, Integer receiptId) {
        tags.put(tagName, receiptId);
        //int number=Integer.parseInt(id);
        //tags.update(number, tagName);
    }

    @GET
    @Path("/{tag}")
    public List<ReceiptResponse> getbytag(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptsRecords = tags.receiptsWithTag(tagName);

        return receiptsRecords.stream().map(ReceiptResponse::new).collect(toList());
    }

    @GET
    public List<TagResponse> getReceipts() {
        List<TagsRecord> tagsRecords = tags.getAllTags();
        return tagsRecords.stream().map(TagResponse::new).collect(toList());
    }
}