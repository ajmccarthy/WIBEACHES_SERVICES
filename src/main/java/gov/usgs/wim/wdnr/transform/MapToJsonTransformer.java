package gov.usgs.wim.wdnr.transform;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class MapToJsonTransformer implements ITransformer {
    private static final Logger LOG = LoggerFactory.getLogger(MapToJsonTransformer.class);

    static final String TOTAL_ROWS = "total_rows";
    public static final String COUNT_HEADER_NAME = "X-Total-Count";

    private OutputStream target;
    private JsonFactory f;
    private HttpServletResponse response;
    private boolean firstRow = true;

    JsonGenerator g;

    public MapToJsonTransformer(HttpServletResponse response) {
        try {
            this.target = new BufferedOutputStream(response.getOutputStream());
        } catch (IOException e) {
            String msgText = "Unable to get output stream";
            LOG.error(msgText, e);
            throw new RuntimeException(msgText, e);
        }
        this.response = response;
        f = new JsonFactory();
        try {
            g = f.createGenerator(target);
        } catch (IOException e) {
            throw new RuntimeException("Error building json generator", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void write(Object result) {
        if (null == result) {
            return;
        }

        if (result instanceof Map) {
            Map<String, Object> resultMap = (Map<String, Object>) result;
            if (firstRow) {
                addResponseHeaders(resultMap);
                initJson();
                firstRow = false;
            }
            writeMap(resultMap);
        }

        try {
            target.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error flushing OutputStream", e);
        }
    }

    /** output the closing tags and flush the stream. */
    @Override
    public void end() {
        try {
            if (null != g) {
                g.close();
            }
            target.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error ending json document", e);
        }
    }

    /** gets called only once, on the first row */
    protected void addResponseHeaders(Map<String, Object> resultMap) {
        response.setHeader(COUNT_HEADER_NAME, getValue(resultMap, TOTAL_ROWS.toUpperCase()));
    }

    /** gets called only once, on the first row */
    protected void initJson() {
        try {
            g.writeStartArray();
        } catch (IOException e) {
            throw new RuntimeException("Error writing json", e);
        }
    };

    /** gets called multiple times, once per row */
    protected void writeMap(Map<String, Object> resultMap) {
        try {
            g.writeStartObject();

            for (Entry<String, Object> entry : resultMap.entrySet()) {
                if (null != entry.getValue() && !TOTAL_ROWS.equalsIgnoreCase(entry.getKey())) {
                    g.writeStringField(entry.getKey(), entry.getValue().toString());
                }
            }

            g.writeEndObject();
        } catch (IOException e) {
            throw new RuntimeException("Error writing json", e);
        }
    }

    @Override
    public void close() throws Exception {
        // do nothing, just like OutputStream
    }

    /** Returns the toString of the object for the given key, or empty string if the object is not in the map, or is null */
    protected String getValue(Map<String, Object> resultMap, String key) {
        if (resultMap.containsKey(key) && null != resultMap.get(key)) {
            return resultMap.get(key).toString();
        } else {
            return "";
        }
    }

}
