package com.example.cachwithdraw;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.example.cachwithdraw")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cach_ransfer.json, html:target/cach_transfer.html")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/com/example/cachwithdraw/Account_Holder_transfers_cash.feature")
@ConfigurationParameter(key=GLUE_PROPERTY_NAME,value="com.example.cachwithdraw")
public class RunCucumberCachTransferTest {
}
