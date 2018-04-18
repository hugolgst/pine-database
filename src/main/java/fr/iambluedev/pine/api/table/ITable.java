package fr.iambluedev.pine.api.table;

import java.util.List;

import fr.iambluedev.pine.api.field.IField;
import fr.iambluedev.pine.api.field.IFields;

public interface ITable {

	Object select(String field);
	
	List<IFields> selectAll();
	
	Object select(String field, IField where);
	
	List<IField> select(List<String> fields, IField where);
	
	Object select(String field, List<IField> where);
	
	List<IField> select(List<String> fields, List<IField> where);

	List<Object> selectList(String field);
	
	List<Object> selectList(String field, IField where);
	
	List<Object> selectList(String field, List<IField> where);
	
	List<IFields> selectList(List<IField> where);
	
	boolean insert(List<IField> fields);
	
	boolean delete(IField where);
	
	boolean update(IField toSet, IField where);
	
	boolean update(List<IField> toSet, IField where);
	
	void dump();
	
	boolean exist(IField field);

	boolean exist(String field, List<IField> where);
}
