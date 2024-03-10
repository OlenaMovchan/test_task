package com.movchan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessFile.class);

    public void processing(String path) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                int num = Integer.parseInt(line.trim());
                numbers.add(num);
            }

            List<Integer> increasingSequence = findLargestIncreasingSequence(numbers);
            List<Integer> descendingSequence = findLArgestDescendingSequence(numbers);

            Collections.sort(numbers);

            int max = Collections.max(numbers);
            int min = Collections.min(numbers);

            double median = findMedian(numbers);
            double average = findAverage(numbers);

            LOGGER.info("Max: " + max);
            LOGGER.info("Min: " + min);
            LOGGER.info("Median: " + median);
            LOGGER.info("Average: " + average);
            LOGGER.info("Maximum increasing sequence " + increasingSequence);
            LOGGER.info("Maximum descending sequence " + descendingSequence);

        } catch (IOException e) {
            LOGGER.error("Error", e);
        }

    }

    public double findMedian(List<Integer> numbers) {
        double median = 0;
        if (numbers.size() % 2 == 0) {
            int index = numbers.size() / 2 - 1;
            median = (numbers.get(index) + numbers.get(numbers.size() / 2)) / 2;
        } else {
            int index1 = numbers.size() / 2;
            median = numbers.get(index1);
        }
        return median;
    }

    public double findAverage(List<Integer> numbers) {
        double sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }
        return sum / numbers.size();
    }

    public List<Integer> findLargestIncreasingSequence(List<Integer> numbers) {
        List<Integer> current = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        if (numbers.size() == 0) {
            return result;
        }

        current.add(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > numbers.get(i - 1)) {
                current.add(numbers.get(i));
            } else {
                if (current.size() > result.size()) {
                    result = new ArrayList<>(current);
                }
                current.clear();
                current.add(numbers.get(i));
            }
        }

        if (current.size() > result.size()) {
            result = new ArrayList<>(current);
        }

        return result;
    }

    public List<Integer> findLArgestDescendingSequence(List<Integer> numbers) {
        List<Integer> current = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        if (numbers.size() == 0) {
            return result;
        }

        current.add(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) < numbers.get(i - 1)) {
                current.add(numbers.get(i));
            } else {
                if (current.size() > result.size()) {
                    result = new ArrayList<>(current);
                }
                current.clear();
                current.add(numbers.get(i));
            }
        }

        if (current.size() > result.size()) {
            result = new ArrayList<>(current);
        }

        return result;
    }

}
