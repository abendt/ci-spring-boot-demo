(ns lambdacd-pipeline.core
  (:require
    [lambdacd-pipeline.pipeline :as pipeline]
    [lambdacd-pipeline.ui-selection :as ui-selection]
    [org.httpkit.server :as http-kit]
    [lambdacd.runners :as runners]
    [lambdacd.util :as util]
    [lambdacd.core :as lambdacd]
    [lambdacd-mongodb.mongodb-state :as mongodb-state]
    [clojure.tools.logging :as log])
  (:gen-class))

(defn -main [& args]
  (let [
        pipeline pipeline/pipeline-def

        ;; the home dir is where LambdaCD saves all data.
        ;; point this to a particular directory to keep builds around after restarting
        home-dir (util/create-temp-dir)

        mongodb-cfg {
                     :uri                                 "mongodb://localhost:27017/lambdacd"
                     :hosts                               ["localhost"]
                     :port                                27017
                     :db                                  "lambdacd"
                     :col                                 "test-project"
                     :max-builds                          10
                     :ttl                                 7
                     :mark-running-steps-as               :killed
                     :pipeline-def                        pipeline
                     :persist-the-output-of-running-steps false
                     :use-readable-build-numbers          true}

        config {:mongodb-cfg mongodb-cfg
                :home-dir    home-dir
                :name        "lambdacd pipeline"}

        ;; initialize and wire everything together
        pipeline (lambdacd/assemble-pipeline pipeline config (mongodb-state/new-mongodb-state config))

        ;; create a Ring handler for the UI
        app (ui-selection/ui-routes pipeline)]
    (log/info "LambdaCD Home Directory is " home-dir)

    ;; this starts the pipeline and runs one build after the other.
    ;; there are other runners and you can define your own as well.
    (runners/start-one-run-after-another pipeline)

    ;; start the webserver to serve the UI
    (http-kit/run-server app {:open-browser? false
                              :port          8090})))
