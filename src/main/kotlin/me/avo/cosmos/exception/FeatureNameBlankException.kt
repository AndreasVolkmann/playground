package me.avo.cosmos.exception

import org.aspectj.lang.Signature

class FeatureNameBlankException(signature: Signature) : IllegalArgumentException(
    "The feature name cannot be blank. Location: ${signature.declaringType.simpleName}.${signature.name}"
)