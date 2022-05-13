import { StyleSheet, Dimensions } from "react-native";

const styles = StyleSheet.create({
    container:{
        margin:20,
    },
    searchBox:{
        flexDirection:'column',
    },
    textInput:{
        marginTop:50,
        fontSize:20,
        marginBottom:20
    },
    row:{
        flexDirection:'row',
        alignItems:'center',
        paddingVertical:15,
        borderBottomWidth:1,
        borderColor:'lightgrey'
    },
    iconContainer:{
        backgroundColor:'#e7e7e7',
        padding:8,
        borderRadius:10,
        marginRight:15
    },
    locationText:{

    }
})

export default styles;