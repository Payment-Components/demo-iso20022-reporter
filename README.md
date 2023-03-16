<a id="logo" href="https://www.paymentcomponents.com" title="Payment Components" target="_blank">
    <img loading="lazy" src="https://i.postimg.cc/yN5TNy29/LOGO-HORIZONTAL2.png" alt="Payment Components">
</a>

# ISO20022 Reporter Demo

With this project, you can create beautiful pdf reports for ISO20022 messages.

## SDK setup
Incorporate the SDK jar into your project by the regular IDE means.
This process will vary depending upon your specific IDE and you should consult your documentation on how to deploy a bean.
For example in Eclipse all that needs to be done is to import the jar files into a project.
Alternatively, you can import it as a Maven or Gradle dependency.  

##### Maven
Define repository in the repositories section
```xml
<repository>
    <id>paymentcomponents</id>
    <url>https://nexus.paymentcomponents.com/repository/mx_reporter</url>
</repository>
```
Import the SDK
```xml
<dependency>
    <groupId>gr.datamation.mx.report</groupId>
    <artifactId>swift-mx-reporter</artifactId>
    <version>1.3.0</version>
    <classifier>min</classifier>
    <!-- OR   -->
    <classifier>standalone</classifier>
</dependency>
```

##### Gradle
Define repository in the repositories section
```groovy
repositories {
    maven {
        url "https://nexus.paymentcomponents.com/repository/mx_reporter"
    }
}
```
Import the SDK
```groovy
implementation 'gr.datamation.mx.report:swift-mx-reporter:1.3.0:min'
//OR
implementation 'gr.datamation.mx.report:swift-mx-reporter:1.3.0-SNAPSHOT:standalone'
```
In case you purchase the SDK you will be given a protected Maven repository with a username and a password. You can configure your project to download the SDK from there.

#### Swift MX dependency
In order for the reporter to work, you also need to include the swift mx dependency.
##### Maven
```xml
<dependency>
    <groupId>gr.datamation.mx</groupId>
    <artifactId>mx</artifactId>
    <version>22.6.0</version>
    <classifier>mx-reporter</classifier>
</dependency>
```
##### Gradle
```groovy
    implementation "gr.datamation.mx:mx:22.6.0:{CLIENT_REPO}"
```

#### Other dependencies
In case you chose the `min` classifier and manually imported the sdk, you need to import a number of other dependencies.
You can use maven or gradle to add the dependencies below or manually include the jar to your project.

##### Maven
```xml
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>kernel</artifactId>
    <version>7.2.1</version>
</dependency>
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>io</artifactId>
    <version>7.2.1</version>
</dependency>
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>layout</artifactId>
    <version>7.2.1</version>
</dependency>
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>html2pdf</artifactId>
    <version>4.0.1</version>
</dependency>
<dependency>
    <groupId>com.github.celtric</groupId>
    <artifactId>kotlin-html</artifactId>
    <version>0.1.4</version>
</dependency>
```
##### Gradle
```groovy
implementation "com.itextpdf:kernel:7.2.1"
implementation "com.itextpdf:io:7.2.1"
implementation "com.itextpdf:layout:7.2.1"
implementation "com.itextpdf:html2pdf:4.0.1"
implementation "com.github.celtric:kotlin-html:0.1.4"
```

## HOW-TO Use our SDK
This project uses the `itext` library in order to create pdf reports.  
In order to create a report, you need to instantiate the `Reporter` class by passing the Path to the html resources.  
You can find an example of css styling [here](./src/main/resources/html-resources/css/style.css). Note that you can declare the path for the bank logo inside `.header-container`.    
Then, you can call `buildReport` by passing the message Object or message Path.
```java
   Reporter instance = new Reporter(Paths.get("./src/main/resources/html-resources"));

    instance.buildReport(
            Paths.get("./src/main/resources/samples/camt.053.001.09.xml"),
            Paths.get("./camt.053.001.09.pdf"),
            new InternalData("Internal ID", "Internal Status"),
            Collections.emptyMap()
    );
    
    //OR
    BankToCustomerStatement09 camt053 = new BankToCustomerStatement09();
    //build message
    instance.buildReport(
        camt053.getMessage(),
        Paths.get("./camt.053.001.09.pdf"),
        new InternalData("Internal ID", "Internal Status"),
        Collections.emptyMap()
    );

    //OR
    String messageXml = "to be completed...";
    instance.buildReport(
        messageXml,
        Paths.get("./camt.053.001.09.pdf"),
        new InternalData("Internal ID", "Internal Status"),
        Collections.emptyMap()
    );

```

### External information
You have the option to provide external information to the report. All you have to do is to pass a `Map` in `buildReport()` with the data you want to show.  
It is recommended to use a `LinkedHashMap` in order to preserve the order of the data.  
```java
    instance.buildReport(
        messageXml,
        Paths.get("./camt.053.001.09.pdf"),
        new InternalData("Internal ID", "Internal Status"),
        new LinkedHashMap<String, String>() {{
            put("Sender", "Bank A");
            put("Receiver", "Bank B");
        }}
    );
```

## Supported Messages
| ISO20022 Message | Library Object class                    | 
|---------------|-----------------------------------------|
| camt.053.001.01 | BankToCustomerStatement01               |
| camt.053.001.02 | BankToCustomerStatement02               |
| camt.053.001.03 | BankToCustomerStatement03               |
| camt.053.001.04 | BankToCustomerStatement04               |
| camt.053.001.05 | BankToCustomerStatement05               |
| camt.053.001.06 | BankToCustomerStatement06               |
| camt.053.001.07 | BankToCustomerStatement07               |
| camt.053.001.08 | BankToCustomerStatement08               |
| camt.053.001.09 | BankToCustomerStatement09               |
| camt.054.001.01 | BankToCustomerDebitCreditNotification01 |
| camt.054.001.02 | BankToCustomerDebitCreditNotification02 |
| camt.054.001.03 | BankToCustomerDebitCreditNotification03 |
| camt.054.001.04 | BankToCustomerDebitCreditNotification04 |
| camt.054.001.05 | BankToCustomerDebitCreditNotification05 |
| camt.054.001.06 | BankToCustomerDebitCreditNotification06 |
| camt.054.001.07 | BankToCustomerDebitCreditNotification07 |
| camt.054.001.08 | BankToCustomerDebitCreditNotification08 |
| camt.054.001.09 | BankToCustomerDebitCreditNotification09 |
| pacs.004.001.04 | PaymentReturn04                         |
| pacs.004.001.05 | PaymentReturn05                         |
| pacs.004.001.06 | PaymentReturn06                         |
| pacs.004.001.07 | PaymentReturn07                         |
| pacs.004.001.08 | PaymentReturn08                         |
| pacs.004.001.09 | PaymentReturn09                         |
| pacs.004.001.10 | PaymentReturn10                         |
| pacs.004.001.11 | PaymentReturn11                         |
| pacs.008.001.04 | FIToFICustomerCreditTransfer04          |
| pacs.008.001.05 | FIToFICustomerCreditTransfer05          |
| pacs.008.001.06 | FIToFICustomerCreditTransfer06          |
| pacs.008.001.07 | FIToFICustomerCreditTransfer07          |
| pacs.008.001.08 | FIToFICustomerCreditTransfer08          |
| pacs.008.001.09 | FIToFICustomerCreditTransfer09          |
| pacs.008.001.10 | FIToFICustomerCreditTransfer10          |
| pacs.009.001.04 | FinancialInstitutionCreditTransfer04    |
| pacs.009.001.05 | FinancialInstitutionCreditTransfer05    |
| pacs.009.001.06 | FinancialInstitutionCreditTransfer06    |
| pacs.009.001.07 | FinancialInstitutionCreditTransfer07    |
| pacs.009.001.08 | FinancialInstitutionCreditTransfer08    |
| pacs.009.001.09 | FinancialInstitutionCreditTransfer09    |
| pacs.009.001.10 | FinancialInstitutionCreditTransfer10    |