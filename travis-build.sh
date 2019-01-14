#!/usr/bin/env bash

set -e
./gradlew clean check assemble --stacktrace

EXIT_STATUS=0
echo "Publishing archives for branch $TRAVIS_BRANCH"
if [[ -n $TRAVIS_TAG ]] || [[ $TRAVIS_BRANCH == 'master' && $TRAVIS_PULL_REQUEST == 'false' ]]; then
    echo "Pushing build to Bintray"
    ./gradlew bintrayUpload || EXIT_STATUS=$?i
fi
exit $EXIT_STATUS
fi