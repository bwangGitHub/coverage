/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.commonjs.module.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;


/**
 * A URL-based script provider that can load modules against a set of base
 * privileged and fallback URIs. It is deliberately not named "URI provider"
 * but a "URL provider" since it actually only works against those URIs that
 * are URLs (and the JRE has a protocol handler for them). It creates cache
 * validators that are suitable for use with both file: and http: URL
 * protocols. Specifically, it is able to use both last-modified timestamps and
 * ETags for cache revalidation, and follows the HTTP cache expiry calculation
 * model, and allows for fallback heuristic expiry calculation when no server
 * specified expiry is provided.
 * @version $Id: UrlModuleSourceProvider.java,v 1.4 2011/04/07 20:26:12 hannes%helma.at Exp $
 */
public class UrlModuleSourceProvider extends ModuleSourceProviderBase
{
  static {
    CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.ping();
  }

    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[1]++;
  }

    private final Iterable<URI> privilegedUris;
    private final Iterable<URI> fallbackUris;
    private final UrlConnectionSecurityDomainProvider
        urlConnectionSecurityDomainProvider;
    private final UrlConnectionExpiryCalculator urlConnectionExpiryCalculator;

    /**
     * Creates a new module script provider that loads modules against a set of
     * privileged and fallback URIs. It will use a fixed default cache expiry
     * of 60 seconds, and provide no security domain objects for the resource.
     * @param privilegedUris an iterable providing the privileged URIs. Can be
     * null if no privileged URIs are used.
     * @param fallbackUris an iterable providing the fallback URIs. Can be
     * null if no fallback URIs are used.
     */
    public UrlModuleSourceProvider(Iterable<URI> privilegedUris,
            Iterable<URI> fallbackUris)
    {
        this(privilegedUris, fallbackUris,
                new DefaultUrlConnectionExpiryCalculator(), null);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[2]++;
    }

    /**
     * Creates a new module script provider that loads modules against a set of
     * privileged and fallback URIs. It will use the specified heuristic cache
     * expiry calculator and security domain provider.
     * @param privilegedUris an iterable providing the privileged URIs. Can be
     * null if no privileged URIs are used.
     * @param fallbackUris an iterable providing the fallback URIs. Can be
     * null if no fallback URIs are used.
     * @param urlConnectionExpiryCalculator the calculator object for heuristic
     * calculation of the resource expiry, used when no expiry is provided by
     * the server of the resource. Can be null, in which case the maximum age
     * of cached entries without validation will be zero.
     * @param urlConnectionSecurityDomainProvider object that provides security
     * domain objects for the loaded sources. Can be null, in which case the
     * loaded sources will have no security domain associated with them.
     */
    public UrlModuleSourceProvider(Iterable<URI> privilegedUris,
            Iterable<URI> fallbackUris,
            UrlConnectionExpiryCalculator urlConnectionExpiryCalculator,
            UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider)
    {
        this.privilegedUris = privilegedUris;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[3]++;
        this.fallbackUris = fallbackUris;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[4]++;
        this.urlConnectionExpiryCalculator = urlConnectionExpiryCalculator;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[5]++;
        this.urlConnectionSecurityDomainProvider =
            urlConnectionSecurityDomainProvider;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[6]++;
    }

    @Override
    protected ModuleSource loadFromPrivilegedLocations(
            String moduleId, Object validator)
            throws IOException, URISyntaxException
    {
        return loadFromPathList(moduleId, validator, privilegedUris);
    }

    @Override
    protected ModuleSource loadFromFallbackLocations(
            String moduleId, Object validator)
            throws IOException, URISyntaxException
    {
        return loadFromPathList(moduleId, validator, fallbackUris);
    }

    private ModuleSource loadFromPathList(String moduleId,
            Object validator, Iterable<URI> paths)
            throws IOException, URISyntaxException
    {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paths == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[1]++;
            return null;

        } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[2]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[1]++;


        for (URI path : paths) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[1]--;
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[2]--;
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[3]++;
}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[9]++;
            final ModuleSource moduleSource = loadFromUri(
                    path.resolve(moduleId), path, validator);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((moduleSource != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[3]++;
                return moduleSource;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[4]++;}
        }
        return null;
    }

    @Override
    protected ModuleSource loadFromUri(URI uri, URI base, Object validator)
    throws IOException, URISyntaxException
    {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[11]++;
        // We expect modules to have a ".js" file name extension ...
        URI fullUri = new URI(uri + ".js");
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[12]++;
        ModuleSource source = loadFromActualUri(fullUri, base, validator);
        // ... but for compatibility we support modules without extension,
        // or ids with explicit extension.
        return source != null ?
               source : loadFromActualUri(uri, base, validator);
    }

    protected ModuleSource loadFromActualUri(URI uri, URI base, Object validator)
    throws IOException
    {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[13]++;
        final URL url = new URL(base == null ? null : base.toURL(), uri.toString());
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[14]++;
        final long request_time = System.currentTimeMillis();
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[15]++;
        final URLConnection urlConnection = openUrlConnection(url);
        final URLValidator applicableValidator;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((validator instanceof URLValidator) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[5]++;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[17]++;
            final URLValidator uriValidator = ((URLValidator)validator);
            applicableValidator = uriValidator.appliesTo(uri) ? uriValidator :
                null;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[18]++;

        }
        else {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[6]++;
            applicableValidator = null;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[19]++;
        }
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((applicableValidator != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[7]++;
            applicableValidator.applyConditionals(urlConnection);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[21]++;

        } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[8]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[22]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            urlConnection.connect();
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[23]++;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
            if((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((applicableValidator != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((applicableValidator.updateValidator(urlConnection,
                            request_time, urlConnectionExpiryCalculator)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false))
            {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[10]++;
                close(urlConnection);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[25]++;
                return NOT_MODIFIED;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[11]++;}

            return new ModuleSource(getReader(urlConnection),
                    getSecurityDomain(urlConnection), uri, base,
                    new URLValidator(uri, urlConnection, request_time,
                            urlConnectionExpiryCalculator));
        }
        catch(FileNotFoundException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[12]++;
            return null;
        }
        catch(RuntimeException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[13]++;
            close(urlConnection);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[26]++;
            throw e;
        }
        catch(IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[14]++;
            close(urlConnection);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[27]++;
            throw e;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[9]++;
}
  }
    }

    private static Reader getReader(URLConnection urlConnection)
    throws IOException
    {
        return new InputStreamReader(urlConnection.getInputStream(),
                getCharacterEncoding(urlConnection));
    }

    private static String getCharacterEncoding(URLConnection urlConnection) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[28]++;
        final ParsedContentType pct = new ParsedContentType(
                urlConnection.getContentType());
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[29]++;
        final String encoding = pct.getEncoding();
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((encoding != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[15]++;
            return encoding;

        } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[16]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[31]++;
        final String contentType = pct.getContentType();
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((contentType != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((contentType.startsWith("text/")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[17]++;
            return "8859_1";

        }
        else {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[18]++;
            return "utf-8";
        }
    }

    private Object getSecurityDomain(URLConnection urlConnection) {
        return urlConnectionSecurityDomainProvider == null ? null :
            urlConnectionSecurityDomainProvider.getSecurityDomain(
                    urlConnection);
    }

    private void close(URLConnection urlConnection) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[33]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            urlConnection.getInputStream().close();
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[34]++;
        }
        catch(IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[20]++;
            onFailedClosingUrlConnection(urlConnection, e);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[35]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[19]++;
}
  }
    }

    /**
     * Override if you want to get notified if the URL connection fails to
     * close. Does nothing by default.
     * @param urlConnection the connection
     * @param cause the cause it failed to close.
     */
    protected void onFailedClosingUrlConnection(URLConnection urlConnection,
            IOException cause) {
    }

    /**
     * Can be overridden in subclasses to customize the URL connection opening
     * process. By default, just calls {@link URL#openConnection()}.
     * @param url the URL
     * @return a connection to the URL.
     * @throws IOException if an I/O error occurs.
     */
    protected URLConnection openUrlConnection(URL url) throws IOException {
        return url.openConnection();
    }

    @Override
    protected boolean entityNeedsRevalidation(Object validator) {
        return !(validator instanceof URLValidator)
                || ((URLValidator)validator).entityNeedsRevalidation();
    }

    private static class URLValidator implements Serializable {
        private static final long serialVersionUID = 1L;

        private final URI uri;
        private final long lastModified;
        private final String entityTags;
        private long expiry;

        public URLValidator(URI uri, URLConnection urlConnection,
                long request_time, UrlConnectionExpiryCalculator
                urlConnectionExpiryCalculator) {
            this.uri = uri;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[36]++;
            this.lastModified = urlConnection.getLastModified();
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[37]++;
            this.entityTags = getEntityTags(urlConnection);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[38]++;
            expiry = calculateExpiry(urlConnection, request_time,
                    urlConnectionExpiryCalculator);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[39]++;
        }

        boolean updateValidator(URLConnection urlConnection, long request_time,
                UrlConnectionExpiryCalculator urlConnectionExpiryCalculator)
        throws IOException
        {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[40]++;
            boolean isResourceChanged = isResourceChanged(urlConnection);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
            if((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isResourceChanged) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[21]++;
                expiry = calculateExpiry(urlConnection, request_time,
                        urlConnectionExpiryCalculator);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[42]++;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[22]++;}
            return isResourceChanged;
        }

        private boolean isResourceChanged(URLConnection urlConnection)
        throws IOException {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;
            if((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((urlConnection instanceof HttpURLConnection) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[23]++;
                return ((HttpURLConnection)urlConnection).getResponseCode() ==
                    HttpURLConnection.HTTP_NOT_MODIFIED;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[24]++;}
            return lastModified == urlConnection.getLastModified();
        }

        private long calculateExpiry(URLConnection urlConnection,
                long request_time, UrlConnectionExpiryCalculator
                urlConnectionExpiryCalculator)
        {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
            if((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 (("no-cache".equals(urlConnection.getHeaderField("Pragma"))) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[25]++;
                return 0L;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[26]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[45]++;
            final String cacheControl = urlConnection.getHeaderField(
                    "Cache-Control");
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;
            if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((cacheControl != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false) ) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[27]++;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
                if((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((cacheControl.indexOf("no-cache") != -1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[29]++;
                    return 0L;

                } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[30]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[48]++;
                final int max_age = getMaxAge(cacheControl);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[49]++;
int CodeCoverConditionCoverageHelper_C13;
                if((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((-1 != max_age) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[31]++;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[50]++;
                    final long response_time = System.currentTimeMillis();
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[51]++;
                    final long apparent_age = Math.max(0, response_time -
                            urlConnection.getDate());
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[52]++;
                    final long corrected_received_age = Math.max(apparent_age,
                            urlConnection.getHeaderFieldInt("Age", 0) * 1000L);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[53]++;
                    final long response_delay = response_time - request_time;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[54]++;
                    final long corrected_initial_age = corrected_received_age +
                        response_delay;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[55]++;
                    final long creation_time = response_time -
                        corrected_initial_age;
                    return max_age * 1000L + creation_time;

                } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[32]++;}

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[28]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[56]++;
            final long explicitExpiry = urlConnection.getHeaderFieldDate(
                    "Expires", -1L);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[57]++;
int CodeCoverConditionCoverageHelper_C14;
            if((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((explicitExpiry != -1L) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[33]++;
                return explicitExpiry;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[34]++;}
            return urlConnectionExpiryCalculator == null ? 0L :
                urlConnectionExpiryCalculator.calculateExpiry(urlConnection);
        }

        private int getMaxAge(String cacheControl) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[58]++;
            final int maxAgeIndex = cacheControl.indexOf("max-age");
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[59]++;
int CodeCoverConditionCoverageHelper_C15;
            if((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((maxAgeIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[35]++;
                return -1;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[36]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[60]++;
            final int eq = cacheControl.indexOf('=', maxAgeIndex + 7);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[61]++;
int CodeCoverConditionCoverageHelper_C16;
            if((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((eq == -1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[37]++;
                return -1;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[38]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[62]++;
            final int comma = cacheControl.indexOf(',', eq + 1);
            final String strAge;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[63]++;
int CodeCoverConditionCoverageHelper_C17;
            if((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((comma == -1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[39]++;
                strAge = cacheControl.substring(eq + 1);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[64]++;

            }
            else {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[40]++;
                strAge = cacheControl.substring(eq + 1, comma);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[65]++;
            }
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[66]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
            try {
CodeCoverTryBranchHelper_Try3 = true;
                return Integer.parseInt(strAge);
            }
            catch(NumberFormatException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[42]++;
                return -1;
            } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[41]++;
}
  }
        }

        private String getEntityTags(URLConnection urlConnection) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[67]++;
            final List<String> etags = urlConnection.getHeaderFields().get("ETag");
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[68]++;
int CodeCoverConditionCoverageHelper_C18;
            if((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((etags == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((etags.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[43]++;
                return null;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[44]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[69]++;
            final StringBuilder b = new StringBuilder();
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[70]++;
            final Iterator<String> it = etags.iterator();
            b.append(it.next());
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[71]++;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[72]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[4]++;


int CodeCoverConditionCoverageHelper_C19;
            while((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[4]--;
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[5]--;
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.loops[6]++;
}
                b.append(", ").append(it.next());
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[73]++;
            }
            return b.toString();
        }

        boolean appliesTo(URI uri) {
            return this.uri.equals(uri);
        }

        void applyConditionals(URLConnection urlConnection) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[74]++;
int CodeCoverConditionCoverageHelper_C20;
            if((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((lastModified != 0L) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[45]++;
                urlConnection.setIfModifiedSince(lastModified);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[75]++;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[46]++;}
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[76]++;
int CodeCoverConditionCoverageHelper_C21;
            if((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((entityTags != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((entityTags.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[47]++;
                urlConnection.addRequestProperty("If-None-Match", entityTags);
CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.statements[77]++;

            } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1.branches[48]++;}
        }

        boolean entityNeedsRevalidation() {
            return System.currentTimeMillis() > expiry;
        }
    }
}

class CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1 ());
  }
    public static long[] statements = new long[78];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-UrlModuleSourceProvider.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2};
    for (int i = 1; i <= 21; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$5om8e3xv4a889jua8fklo4p8ud0g013ix4cr6jvx2xmb9o1af0r4bzmlso1 () {
    super("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-UrlModuleSourceProvider.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 77; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.commonjs.module.provider.RHINO-SRC-UrlModuleSourceProvider.java");
      for (int i = 1; i <= 77; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
        if (loops[i * 3 - 2] != 0L) {
          log.passCounter("L" + i + "-0", loops[i * 3 - 2]);
          loops[i * 3 - 2] = 0L;
        }
        if ( loops[i * 3 - 1] != 0L) {
          log.passCounter("L" + i + "-1", loops[i * 3 - 1]);
          loops[i * 3 - 1] = 0L;
        }
        if ( loops[i * 3] != 0L) {
          log.passCounter("L" + i + "-2", loops[i * 3]);
          loops[i * 3] = 0L;
        }
      }
  }
}
