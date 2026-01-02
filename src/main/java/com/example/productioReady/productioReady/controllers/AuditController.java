package com.example.productioReady.productioReady.controllers;

import com.example.productioReady.productioReady.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping(path = "/posts/{postId}")
    List<PostEntity> getPostsRevisions(@PathVariable Long postId){
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        List<Number> revisions = reader.getRevisions(PostEntity.class, postId);
        return revisions.stream()
                .map(revisionNumber -> reader.find(PostEntity.class, postId, revisionNumber))
                .toList();
    }
}

// This controller provides an endpoint to fetch all revisions of a PostEntity by its ID using Hibernate Envers.
// The revisions are retrieved from the audit tables created by Envers.
// You can test this endpoint by making GET requests to /audit/posts/{postId}, replacing {postId} with the actual ID of the post you want to retrieve revisions for.
// Make sure you have some revisions in the database by updating the posts before testing this endpoint.
// Note: Ensure that Spring Data JPA Auditing is enabled in your application configuration for the auditing fields to be populated correctly.

// We are directly using PostEntity here for simplicity. In a production application, consider using DTOs for better abstraction and security.

// if you create a post and edit it 3 times and hit below endpoint you will get 4 revisions
// GET http://localhost:8080/audit/posts/1

//output
// [
//    {
//        "id": 1,
//        "title": "This is title",
//        "description": "This is description",
//        "createdBy": "Abhinav Kumar Gupta",
//        "createdDate": "2026-01-02T14:49:35.646638",
//        "updatedBy": "Abhinav Kumar Gupta",
//        "updatedDate": "2026-01-02T14:49:35.646638"
//    },
//    {
//        "id": 1,
//        "title": "This is title 1",
//        "description": "This is description 1",
//        "createdBy": "Abhinav Kumar Gupta",
//        "createdDate": "2026-01-02T14:49:35.646638",
//        "updatedBy": "Abhinav Kumar Gupta",
//        "updatedDate": "2026-01-02T14:49:54.570857"
//    },
//    {
//        "id": 1,
//        "title": "This is title 2",
//        "description": "This is description 2",
//        "createdBy": "Abhinav Kumar Gupta",
//        "createdDate": "2026-01-02T14:49:35.646638",
//        "updatedBy": "Abhinav Kumar Gupta",
//        "updatedDate": "2026-01-02T14:50:04.036415"
//    },
//    {
//        "id": 1,
//        "title": "This is title 3",
//        "description": "This is description 3",
//        "createdBy": "Abhinav Kumar Gupta",
//        "createdDate": "2026-01-02T14:49:35.646638",
//        "updatedBy": "Abhinav Kumar Gupta",
//        "updatedDate": "2026-01-02T14:50:12.824079"
//    }
//]