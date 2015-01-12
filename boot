#!/usr/bin/env bash

cd web

gradle clean bootRepackage && cd .. && fig up

fig stop
fig rm --force
fig ps
