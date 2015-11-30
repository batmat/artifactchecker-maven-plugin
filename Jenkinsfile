node {
  // See https://github.com/jenkinsci/workflow-plugin/blob/master/TUTORIAL.md#creating-multibranch-projects
  checkout scm
  def mvnHome = tool 'maven-3'
  sh "${mvnHome}/bin/mvn -B clean verify"
}
