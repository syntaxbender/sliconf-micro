package javaday.istanbul.sliconf.micro.model;

import javaday.istanbul.sliconf.micro.model.event.Sponsor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Sponsor ekleme ve cikarma sirasinda on taraftan gelen veri modeli
 *
 * sponsor => { "sponsorTagId1": [{Sponsor1}, {Sponsor2}],
 *              "sponsorTagId2": [{Sponsor3}, {Sponsor4}] }
 * sponsorTags => { "sponsorTagId1": "Gold",
 *                  "sponsorTagId2": "Bronze" }
 *
 */
@Getter
@Setter
public class SponsorsAndSponsorTags {
    private Map<String, List<Sponsor>> sponsors;
    private Map<String, String> sponsorTags;

}
