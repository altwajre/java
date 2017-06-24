# Scalars

http://www.yaml.org/spec/1.2/spec.html#id2760844

Scalar content can be written in block notation, using a literal style (indicated by "|") 
where all line breaks are significant.

Alternatively, they can be written with the folded style (denoted by ">") 
where each line break is folded to a space unless it ends an empty or a more-indented line.

YAML's flow scalars include the plain style and two quoted styles.
The double-quoted style provides escape sequences.
The single-quoted style is useful when escaping is not needed.
All flow scalars can span multiple lines; line breaks are always folded.
