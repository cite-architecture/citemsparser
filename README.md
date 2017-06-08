# `citemsparser`

## What it is

`citemsparser`  is a cross-platform library to parse replies from CITE microservices, and to validate replies against [specifications for CITE microservices](https://github.com/cite-architecture/cite-services-spec).




## Current version: 1.0.0


Status:  **planning**. [Release notes](releases.md)

## License

[GPL 3.0](https://opensource.org/licenses/gpl-3.0.html)

## Using, building, testing

`citemsparser` is compiled for both the JVM and ScalaJS using scala 2.11.


To build from source and test for a given version, use normal sbt commands (`compile`, `test` ...).


## Examples of usage: lists of nodes

For any `texts` microservice request returning a list of citable nodes, if `reply` is the text content of the reply, then you can obtain a `CitableNodeListReply` object from the `TextReply` factory object:


    val nodesResult = TextReply(reply)

From the `CitableNodeListReply`, you can get either a Vector of `CitableNode`s (from the `ohco2` library):

    nodesResult.citableNodes

or a `Corpus` object (from the `ohco2` library);


    nodesResult.corpus

## Examples of usage: lists of URNs

For any `texts` microservice request returning a list of URNs, if `reply` is the text content of the reply, then you can obtain a `CtsUrnListReply` object from the `TextReply` factory object:


    val urnsResult = TextReply(reply)

From the  `CtsUrnListReply`, you can get a Vector of `CtsUrn`s (from the `xcite` library):

    urnsResult.urns
