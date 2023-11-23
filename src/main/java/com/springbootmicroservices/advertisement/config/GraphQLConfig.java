//package com.springbootmicroservices.advertisement.config;
//import com.coxautodev.graphql.tools.SchemaParser;
//import com.coxautodev.graphql.tools.SchemaParserBuilder;
//import graphql.GraphQL;
//import graphql.schema.GraphQLSchema;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//
//@Configuration
//public class GraphQLConfig {
//
//    private final ResourceLoader resourceLoader;
//
//    public GraphQLConfig(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//
//    @Bean
//    public GraphQLSchema graphQLSchema() throws Exception {
//        // Load the GraphQL schema from the resource file
//        Resource schemaResource = resourceLoader.getResource("classpath:schema.graphqls");
//
//        // Create a SchemaParser with the loaded schema
//        SchemaParserBuilder schemaParserBuilder = SchemaParser.newParser()
//                .files(String.valueOf(schemaResource.getFile()));
//
//        // Build the GraphQLSchema
//        return schemaParserBuilder.build().makeExecutableSchema();
//    }
//
//    @Bean
//    public GraphQL graphQL(GraphQLSchema graphQLSchema) {
//        return GraphQL.newGraphQL(graphQLSchema).build();
//    }
//}
