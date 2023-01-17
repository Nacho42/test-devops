from jenkins/jenkins

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY src/security.groovy /usr/share/jenkins/ref/init.groovy.d/security.groovy

COPY --chown=jenkins:jenkins src/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

COPY src/job.xml /usr/share/jenkins/ref/jobs/CI/config.xml.override