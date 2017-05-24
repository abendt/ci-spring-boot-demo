# lambdacd-pipeline

A continuous delivery pipeline for FIXME

## Dependencies

* mongodb
    * local testing: `docker run --name some-mongo -p 27017:27017 -d mongo`

## Usage

* `lein run` will start your pipeline with a web-ui listening on port 8090

## Files

* `/`
    * `project.clj` contains dependencies and build configuration for Leiningen

* `/src/lambdacd_pipeline/`
    * `pipeline.clj` contains your pipeline-definition
    * `steps.clj` contains your custom build-steps
    * `core.clj` contains the `main` function that wires everything together

* `/resources/`
    * `logback.xml` contains a sample log configuration

## References

* for a more detailed example, look at the [example pipeline](https://github.com/flosell/lambdacd/tree/master/src/todopipeline) in the main LambdaCD project
