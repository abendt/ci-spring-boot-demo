(ns lambdacd-pipeline.steps
    (:require [lambdacd.steps.shell :as shell]
      [lambdacd-git.core :as lambdacd-git]
      ))

(def repo-uri "https://github.com/abendt/ci-spring-boot-demo.git")
(def repo-branch "master")

(defn some-step-that-does-nothing [args ctx]
      {:status :success})

(defn some-step-that-echos-foo [args ctx]
      (shell/bash ctx "/" "echo foo"))

(defn some-step-that-echos-bar [args ctx]
      (shell/bash ctx "/" "echo bar"))

(defn some-failing-step [args ctx]
      (shell/bash ctx "/" "echo \"i am going to fail now...\"" "exit 1"))

(defn wait-for-repo [args ctx]
      (lambdacd-git/wait-for-git ctx repo-uri :ref (str "refs/heads/" repo-branch)))

(defn clone [args ctx]
      (let [revision (:revision args)
            cwd (:cwd args)
            ref (or revision repo-branch)]
           (lambdacd-git/clone ctx repo-uri ref cwd)))

(defn run-some-tests [args ctx]
      (do
        (shell/bash ctx (:cwd args) "chmod +x ./mvnw")
        (shell/bash ctx (:cwd args) {"MAVEN_OPTS" "-Xmx512m"} "./mvnw verify")
        )
      )