package com.testsdemo.testcrud.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testsdemo.testcrud.models.User;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;


public class CommonUtil {

    private static final String EMAIL_REGEX = "(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private static final Map<String, String> mimeTypeMap;

    private CommonUtil() {
        throw new IllegalStateException("Utility Class");
    }

    static {
        mimeTypeMap = new HashMap<>();
        mimeTypeMap.put("csv", "text/csv");
        mimeTypeMap.put("xls", "application/vnd.ms-excel");
        mimeTypeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static boolean validateEmailFormat(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean validateEmailFormat(List<String> emails) {
        for(String email : emails) {
            if (!validateEmailFormat(email)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStringListContainsEmptyValue(final List<String> list) {
        return list.stream().anyMatch(StringUtils::isBlank);
    }

    public static boolean isMobileNoPrefix(String val) {
        if (StringUtils.isBlank(val)) {
            return false;
        } else {
            val = val.replace("+", "");
            return (!val.startsWith("02") && !val.startsWith("03") && !val.startsWith("04") && !val.startsWith("05") && !val.startsWith("07")) &&
                    (!val.startsWith("662") && !val.startsWith("663") && !val.startsWith("664") && !val.startsWith("665") && !val.startsWith("667"));
        }
    }

    /**
     * <p>
     *     Separate string in list with specify char
     * </p>
     * @param list the string list to be separated, can be null
     * @param separatedString the string to be separate character. if null or empty, default as comma
     * @return separated string with specify char including space after, or null if the list is null or empty
     */
    public static String toSpecifySeparatedString(List<String> list, String separatedString) {
        if (isNullOrEmpty(list)) {
            return null;
        } else {
            if (StringUtils.isBlank(separatedString)) {
                return toCommaSeparatedString(list);
            } else {
                return String.join(separatedString.trim() + StringUtils.SPACE, list);
            }
        }
    }

    public static String toCommaSeparatedString(List<String> list) {
        return toSpecifySeparatedString(list, ",");
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Set<Object> set = ConcurrentHashMap.newKeySet();
        return t -> set.add(keyExtractor.apply(t));
    }

    public static String getString(Map<String, Object> parameters, final String fieldName) {
        if (null != parameters) {
            Object obj = parameters.get(fieldName);
            return Objects.nonNull(obj) ? String.valueOf(obj) : null;
        }
        return null;
    }

    public static Integer getInteger(Map<String, Object> parameters, final String fieldName) {
        if (null != parameters) {
            Object obj = parameters.get(fieldName);
            try {
                return Objects.nonNull(obj) ? Integer.parseInt(obj.toString()) : null;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public static BigDecimal getBigDecimal(Map<String, Object> parameters, final String fieldName) {
        Object obj = parameters.get(fieldName);
        return Objects.nonNull(obj) ? new BigDecimal(String.valueOf(parameters.get(fieldName))) : null;
    }

    public static List<String> getStringAsList(Map<String, Object> parameters, final String fieldName) {
        Object obj = parameters.get(fieldName);
        return Objects.nonNull(obj) ? (ArrayList<String>) parameters.get(fieldName) : Collections.emptyList();
    }

    public static boolean getBoolean(Map<String, Object> parameters, final String fieldName) {
        Object obj = parameters.get(fieldName);
        if (null != obj && StringUtils.isNotBlank(obj.toString())) {
            return BooleanUtils.toBoolean(obj.toString());
        }
        return false;
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return !isNotEmpty(list);
    }

    public static <T> boolean isNotEmpty(List<T> list) {
        return null != list && !list.isEmpty();
    }

    public static Date getDate(Map<String, Object> parameters, final String fieldName) {
        return getDate(parameters, fieldName, null);
    }

    public static Date getDate(Map<String, Object> parameters, final String fieldName, final String dateFormat) {
        Object obj = safeConvert(parameters.get(fieldName));
        if (Objects.nonNull(obj)) {
            String dateStr = String.valueOf(obj);
            if (StringUtils.isBlank(dateFormat)) {
                return DateTimeUtil.toDate(dateStr);
            } else {
                return DateTimeUtil.toDate(dateStr, dateFormat);
            }
        } else {
            return null;
        }
    }

    public static Object safeConvert(Object source) {
        try {
            System.out.println("Safe Convert, Source value: "+source);
            LocalDateTime ldt = DateTimeUtil.toLocalDateTime(source.toString());
            System.out.println("Format to Local DateTime: "+ldt);
            if (null != ldt) {
                DateTime dt = new DateTime(ldt.toDate()).withZone(DateTimeUtil.getBangkokTimeZone());
                System.out.println("After set time zone:"+dt);
                return DateTimeUtil.toDateTimeString(dt, DateTimeUtil.ISO8601_FORMAT);
            }

            throw new IllegalArgumentException("source value is empty");
        } catch (Exception e) {
            return source;
        }
    }

    public static Optional<String> getExtension(String fileName) {
        final String dot = ".";
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains(dot))
                .map(m -> m.substring(fileName.lastIndexOf(dot) + 1));
    }

    public static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    public static <T> List<T> iterableToList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .toList();
    }

    public static User findUserByName(Iterable<User> users, String name){
        return CommonUtil.iterableToList(users).stream().filter(e ->e.getName().toLowerCase().equals(name) ).findFirst().get();
    }

    public static String guessMimeTypeByFileName(String fileName) {
        String mimeType = URLConnection.guessContentTypeFromName(fileName);
        if (StringUtils.isNotBlank(mimeType)) {
            return mimeType;
        } else {
            return getExtension(fileName).map(mimeTypeMap::get).orElse(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        }
    }

    public static List<?> convertToList(Object obj) {
        List<?> list;
        if (null != obj) {
            if (obj.getClass().isArray()) {
                list = Arrays.asList((Object[]) obj);
            } else if (obj instanceof Collection) {
                list = new ArrayList<>((Collection<?>) obj);
            } else {
                throw new IllegalArgumentException("Object is not Array or Collection");
            }
            return list;
        } else {
            return Collections.emptyList();
        }
    }

    public static Map<String, Object> convertToMap(Object obj) {
        return new ObjectMapper().convertValue(obj, ModelMap.class);
    }

    public static <T> byte[] convertToJsonAsBytes(T container) throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(container);
    }

    public static String toFullName(final String firstName, final String lastName) {
        return toFullName(firstName, null, lastName);
    }

    public static String toFullName(final String firstName, final String middleName, final String lastName) {
        final String f = StringUtils.trimToEmpty(firstName);
        final String m = StringUtils.trimToEmpty(middleName);
        final String l = StringUtils.trimToEmpty(lastName);

        StringBuilder builder = new StringBuilder(f);
        builder.append(StringUtils.SPACE);
        if (StringUtils.isNotBlank(m)) {
            builder.append(m).append(StringUtils.SPACE);
        }
        builder.append(l);
        return StringUtils.trimToNull(builder.toString());
    }

    public static String generateUUIDWithNoHyphen() {
        String uuid = UUID.randomUUID().toString();
        return StringUtils.replace(uuid, "-", StringUtils.EMPTY);
    }

    public static boolean getBooleanForSignCert(Map<String, Object> parameters, final String fieldName) {
        Object obj = parameters.get(fieldName);
        if (null != obj && StringUtils.isNotBlank(obj.toString())) {
            return BooleanUtils.toBoolean(obj.toString());
        }
        return true;
    }
}