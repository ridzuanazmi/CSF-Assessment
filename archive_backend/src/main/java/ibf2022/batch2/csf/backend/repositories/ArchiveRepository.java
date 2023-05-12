package ibf2022.batch2.csf.backend.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.csf.backend.models.Archive;

@Repository
public class ArchiveRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	// TODO: Task 4
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	/*
	 * db.archives.insert({
	 * "bundleId" : "c569de06",
	 * "date" : ISODate("2023-05-12T06:33:06.567+0000"),
	 * "title" : "testing3",
	 * "name" : "Danny",
	 * "comments" : "tesintg",
	 * "urls" : [
	 * "https://day37-workshop.sgp1.digitaloceanspaces.com/cute-baby-polar-bear-day-photography-23__880.jpg",
	 * "https://day37-workshop.sgp1.digitaloceanspaces.com/617bcdf2-dba9-4731-9392-93a079f3d52c.jpg",
	 * "https://day37-workshop.sgp1.digitaloceanspaces.com/dov1.gif",
	 * "https://day37-workshop.sgp1.digitaloceanspaces.com/dilbert.jpg",
	 * "https://day37-workshop.sgp1.digitaloceanspaces.com/Michael%20Jacksons%20Moonwalker.png",
	 * "https://day37-workshop.sgp1.digitaloceanspaces.com/PICO-8_CheatSheet_0111Gm_4k.png"
	 * ],
	 * })
	 */
	public void recordBundle(Archive bundle) {
		mongoTemplate.insert(bundle, "archives");
	}

	// TODO: Task 5
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Optional<Archive> getBundleByBundleId(String bundleId) {
		
		Query query = new Query(Criteria.where("bundleId").is(bundleId));
		Archive archive = mongoTemplate.findOne(query, Archive.class, "archives");
		return Optional.ofNullable(archive);
	}

	// TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundles(/* any number of parameters here */) {
		return null;
	}

}
