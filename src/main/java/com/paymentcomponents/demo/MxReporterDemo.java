package com.paymentcomponents.demo;

import gr.datamation.mx.message.camt.BankToCustomerStatement09;
import gr.datamation.mx.report.Reporter;
import gr.datamation.mx.report.model.InternalData;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MxReporterDemo {

    public static void main(String[] args) {
        MxReporterDemo mxReporterDemo = new MxReporterDemo();

        mxReporterDemo.buildCamt053Report();
        mxReporterDemo.buildCamt054Report();
        mxReporterDemo.buildPacs004Report();
        mxReporterDemo.buildPacs008Report();
        mxReporterDemo.buildPacs009Report();

        mxReporterDemo.buildReportFromMessageText();
        mxReporterDemo.buildReportFromMessageObject();
    }

    private void buildCamt053Report() {
        Reporter instance = new Reporter(Paths.get("./src/main/resources/html-resources"));

        instance.buildReport(
                Paths.get("./src/main/resources/samples/camt.053.001.09.xml"),
                Paths.get("./camt.053.001.09.pdf"),
                new InternalData("Internal ID", "Internal Status")
        );
    }

    private void buildCamt054Report() {
        Reporter instance = new Reporter(Paths.get("./src/main/resources/html-resources"));

        instance.buildReport(
                Paths.get("./src/main/resources/samples/camt.054.001.09.xml"),
                Paths.get("./camt.054.001.09.pdf"),
                new InternalData("Internal ID", "Internal Status")
        );
    }

    private void buildPacs004Report() {
        Reporter instance = new Reporter(Paths.get("./src/main/resources/html-resources"));

        instance.buildReport(
                Paths.get("./src/main/resources/samples/pacs.004.001.11.xml"),
                Paths.get("./pacs.004.001.11.pdf"),
                new InternalData("Internal ID", "Internal Status")
        );
    }

    private void buildPacs008Report() {
        Reporter instance = new Reporter(Paths.get("./src/main/resources/html-resources"));

        instance.buildReport(
                Paths.get("./src/main/resources/samples/pacs.008.001.10.xml"),
                Paths.get("./pacs.008.001.10.pdf"),
                new InternalData("Internal ID", "Internal Status")
        );
    }

    private void buildPacs009Report() {
        Reporter instance = new Reporter(Paths.get("./src/main/resources/html-resources"));

        instance.buildReport(
                Paths.get("./src/main/resources/samples/pacs.009.001.10.xml"),
                Paths.get("./pacs.009.001.10.pdf"),
                new InternalData("Internal ID", "Internal Status")
        );
    }

    private void buildReportFromMessageText() {
        try {
            Reporter instance = new Reporter(Paths.get("./src/main/resources/html-resources"));

            Stream<String> lines = Files.lines(Paths.get("./src/main/resources/samples/camt.053.001.09.xml"));

            String messageText = lines.collect(Collectors.joining("\n"));
            lines.close();

            instance.buildReport(
                    messageText,
                    Paths.get("./camt.053.001.09_from_text.pdf"),
                    new InternalData("Internal ID", "Internal Status")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildReportFromMessageObject() {
        try {
            Reporter instance = new Reporter(Paths.get("./src/main/resources/html-resources"));

            Stream<String> lines = Files.lines(Paths.get("./src/main/resources/samples/camt.053.001.09.xml"));

            String messageText = lines.collect(Collectors.joining("\n"));
            lines.close();

            BankToCustomerStatement09 camt053 = new BankToCustomerStatement09();
            camt053.parseXML(messageText);

            instance.buildReport(
                    camt053,
                    Paths.get("./camt.053.001.09_from_object.pdf"),
                    new InternalData("Internal ID", "Internal Status")
            );
        } catch (JAXBException | XMLStreamException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
