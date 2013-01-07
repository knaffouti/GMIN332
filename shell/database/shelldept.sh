#! /bin/bash  

IFS=$':'
while read codereg namereg codedept namedept f1 f2 nbrcom f3 pop
do 
    echo $codereg $codedept $namedept $nbrcom '-' $pop '-'

mysql --user=root --password=rabah123 rdfdatabase <<EOF
   
insert into inseedept values ('$codedept','$namedept','$nbrcom','$pop','$codereg');

EOF

done <$1 2>error.txt



		
